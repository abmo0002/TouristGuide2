package com.example.touristguide.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.example.touristguide.model.TouristAttraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    @Repository
    public class TouristRepositoryDB {

        @Value("${spring.datasource.url}")
        private String db_url;

        @Value("${spring.datasource.username}")
        private String uid;

        @Value("${spring.datasource.password}")
        private String pwd;

        public List<TouristAttraction> getAllAttractions(){
            List<TouristAttraction> attractions = new ArrayList<>();
            String SQL = "SELECT name, description, cityName, GROUP_CONCAT(tagName) AS tags FROM tourist_attraction JOIN city ON tourist_attraction.cityID = city.cityID JOIN tourist_attraction_tag ON tourist_attraction.touristID = tourist_attraction_tag.touristID JOIN tag ON tourist_attraction_tag.tagID = tag.tagID GROUP BY tourist_attraction.name, tourist_attraction.description, city.cityName;";
            try (Connection conn = DriverManager.getConnection(db_url, uid, pwd)){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while(rs.next()){
                    attractions.add(new TouristAttraction(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("cityName"),
                            List.of(rs.getString(4))));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return attractions;
        }
        public List<TouristAttraction> addAttraction(TouristAttraction touristAttraction) {
            List<TouristAttraction> addTouristAttraction = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(db_url, uid, pwd)) {
                int cityid=0;
                int touristID=0;
                String SQL1="SELECT cityID from city where cityName = ? ;";

                String sql2 = "INSERT INTO tourist_attraction (name, description, cityID) VALUES (?, ?, ?);";

                String sql3 = "SELECT touristID FROM tourist_attraction WHERE name=?; ";

                String sql4 = "UPDATE tourist_attraction_tag SET tagID = ? WHERE touristID = ?;";


                PreparedStatement ps = conn.prepareStatement(SQL1);
                ps.setString(1, touristAttraction.getCity());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    cityid=rs.getInt(1);
                }
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, touristAttraction.getName());
                ps2.setString(2, touristAttraction.getDescription());
                ps2.setInt(3,cityid);
                ps2.executeUpdate();

                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1,touristAttraction.getName());
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){
                    touristID=rs3.getInt(1);
                }

                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ps4.setString(1, touristAttraction.getTags().toString());
                ps4.setInt(2, touristID);
                ps4.executeUpdate();

                addTouristAttraction.add(touristAttraction);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return addTouristAttraction;
        }
        public void deleteAttraction(String name){
            try(Connection conn = DriverManager.getConnection(db_url, uid, pwd)) {
                String SQL = "SELECT touristID FROM tourist_attraction WHERE name = ?";
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                int atID = 0;

                while(rs.next()) {
                    atID = rs.getInt(1);
                }

                String SQLChild = "DELETE FROM tourist_attraction_tag WHERE touristID = ?";
                ps = conn.prepareStatement(SQLChild);
                ps.setInt(1, atID);
                ps.executeUpdate();

                String SQLParent = "DELETE FROM tourist_attraction WHERE touristID = ?";
                conn.prepareStatement(SQLParent);
                ps.setInt(1, atID);
                ps.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        public List<String> Tags(String name){
            List<String> tags = new ArrayList<>();
            String SQL = "SELECT tag.tagName FROM tourist_attraction JOIN tourist_attraction_tag ON tourist_attraction.touristID = tourist_attraction_tag.touristID JOIN tag ON tourist_attraction_tag.tagID = tag.tagID WHERE tourist_attraction.name = ?;";

            try(Connection conn = DriverManager.getConnection(db_url, uid, pwd)) {
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    tags.add(rs.getString(1));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return tags;
        }

        public List<String> getTags(){
            List<String> tags = new ArrayList<>();
            try(Connection conn = DriverManager.getConnection(db_url,uid,pwd)) {
                String SQL = "SELECT tagName FROM tag";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()){
                    tags.add(rs.getString("tagName"));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            return tags;
        }

        public List<String> getCities(){
            List<String> cities = new ArrayList<>();
            try(Connection conn = DriverManager.getConnection(db_url,uid,pwd)) {
                String SQL = "SELECT cityName FROM city";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()){
                    cities.add(rs.getString("cityName"));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            return cities;
        }




    }
