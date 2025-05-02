import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetExample {

    // Method to send GET request
    public static String sendGet(String urlString) throws Exception {
        // Create a URL object with the target URL
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set a user-agent for the request (optional but recommended)
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read the response
        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else { // error
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    public static void main(String[] args) {
        try {
            // Example URL to fetch data from
            String url = "https://api.truthordarebot.xyz/api/truth";
            
            // Call the sendGet method and print the response
            String response = sendGet(url);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
