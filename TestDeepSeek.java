import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestDeepSeek {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.deepseek.com/v1/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer sk-2a570f7ab9f544d4bdfea93df6530a51");
            conn.setDoOutput(true);
            
            String jsonInputString = "{" +
                "\"model\": \"deepseek-chat\", " +
                "\"messages\": [" +
                    "{\"role\": \"user\", \"content\": \"你好\"}" +
                "]" +
            "}";
            
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Response: " + response.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}