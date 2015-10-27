/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

/**
 *
 * @author Nipuna
 */
import datadigger.DataGathering.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_Handler {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/rateme";

    private final String USER = "root";
    private final String PASS = "";

    private Connection conn = null;
    private Statement stmt = null;

    private Logger logger = Logger.getLogger("MyLogger");

    public Database_Handler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            System.out.println("Connected database successfully...");
        } catch (Exception e) {
            System.out.println("Connot connect to database");
        }
    }

    // Insert the review in to the DB
    public void insertReviewToDB(Review review) {
        System.out.println("Inserting Records");
        try {
            logger.log(Level.INFO, "Inserting records into the table...");          
            stmt = conn.createStatement();
            String sql = "INSERT INTO `review`(`review_id`, `product_id`, `created_date`, `text`, `api`, `geo_location`, `likes`, `final_sentiment`) VALUES ("+review.getReviewId()+","+review.getProductId()+",'"+review.getCreatedDateString()+"','"+review.getReview()+"','"+review.getAPI()+"','',"+review.getLikes()+",'"+review.getFinalSentiment()+"')";
            stmt.executeUpdate(sql);
            logger.log(Level.INFO, "Inserted records into the table...");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }

    }

    // Insert the features belongs to the review in to the DB
    public void insertFeatureReview(int featureId, String feature, String value, int score, int productId, int reviewId) {
        try {
            logger.log(Level.INFO, "Inserting records into the FeatureReview table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO review_feature "
                    + "VALUES " + "(" + featureId + "," + feature + "," + value + "," + score + "," + reviewId + "," + productId + ")";
            stmt.executeUpdate(sql);

            logger.log(Level.INFO, "Inserted records into the table...");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
    }

    // Last Inserted id for review
    public int getLastInsertedIdForReview() {
        int id = 0;
        try {
            logger.log(Level.INFO, "Get Id review...");
            stmt = conn.createStatement();

            String sql = "SELECT MAX(review_id) AS id FROM review";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return id;
    }

    // Last Inserted id for review
    public int getLastInsertedIdForFeature() {
        int id = 0;
        try {
            logger.log(Level.INFO, "Get Id review...");
            stmt = conn.createStatement();

            String sql = "SELECT MAX(reviewfeature_id) AS id FROM review_feature";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return id;
    }

    // Get features for the category
    public ArrayList<String> loadFeaturesByProductType(int categoryId) {
        ArrayList<String> features = new ArrayList<String>();
        logger.log(Level.INFO, "Getting features for product category from the table...");
        try {
            stmt = conn.createStatement();
            String sql = "SELECT feature_name FROM feature WHERE category_id=" + categoryId;
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {

                features.add(result.getString("feature_name"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return features;
    }

    // Get features for the review
    public ArrayList<Feature> loadFeaturesForReview(int reviewId) {
        ArrayList<Feature> features = new ArrayList<Feature>();
        logger.log(Level.INFO, "Getting feature data for review from the table...");
        try {
            stmt = conn.createStatement();
            String sql = "SELECT feature,value,score FROM review_feature";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                Feature feature = new Feature();
                feature.setFeatureName(result.getString("feature"));
                feature.setScore(result.getInt("score"));
                feature.setValue(result.getString("value"));

                features.add(feature);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return features;
    }

    // Load product details.
    public ArrayList<String> loadProductDetails() {
        ArrayList<String> productDetails = new ArrayList<String>();
        logger.log(Level.INFO, "Getting product data from the table...");
        try {
            stmt = conn.createStatement();
            String sql = "SELECT product_name FROM product";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                productDetails.add(result.getString("product_name"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }

        return productDetails;
    }

    // Load category data
    public ArrayList<String> loadCategoryDetails() {
        ArrayList<String> categoryDetails = new ArrayList<String>();
        logger.log(Level.INFO, "Getting category data from the table...");
        try {
            stmt = conn.createStatement();
            String sql = "SELECT category_name FROM category";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                categoryDetails.add(result.getString("category_name"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }

        return categoryDetails;
    }

    // Get the category by product ID
    public String getCategoryByProductId(int productId) {
        String category = "";
        try {
            int category_id;
            stmt = conn.createStatement();
            String sql = "SELECT category_id FROM product WHERE product_id=" + productId;
            ResultSet result_1 = stmt.executeQuery(sql);
            while (result_1.next()) {
                category_id = result_1.getInt("category_id");
            }

            String sql_2 = "SELECT category_name FROM category WHERE product_id=" + productId;
            ResultSet result_2 = stmt.executeQuery(sql_2);
            while (result_2.next()) {
                category = result_2.getString("category_name");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return category;
    }

    // Get the category ID by product ID
    public int getCategoryIdByProductId(int productId) {
        int category_id = 0;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT category_id FROM product WHERE product_id=" + productId;
            ResultSet result_1 = stmt.executeQuery(sql);
            while (result_1.next()) {
                category_id = result_1.getInt("category_id");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }
        return category_id;
    }

    // Load product ID
    public int loadProductId(String productName) {
        int productId = 0;
        logger.log(Level.INFO, "Getting product data from the table...");
        try {
            stmt = conn.createStatement();
            String sql = "SELECT product_id FROM product WHERE product_name= '" + productName + "'";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                productId = result.getInt("product_id");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            closeConnections();
        }

        return productId;
    }

    // Close the data connection.
    public void closeConnections() {
        //finally block used to close resources
        try {
            if (stmt != null) {
                conn.close();
            }
        } catch (SQLException se) {
        }// do nothing
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }//end finally try
    }
}
