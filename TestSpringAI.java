import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestSpringAI {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestSpringAI.class, args);
        ChatModel chatModel = context.getBean(ChatModel.class);
        
        try {
            System.out.println("Testing ChatModel.call(String)...");
            String response = chatModel.call("你好");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Error calling chatModel.call(String):");
            e.printStackTrace();
        }
        
        try {
            System.out.println("\nTesting ChatModel.call(Prompt)...");
            Prompt prompt = new Prompt(new UserMessage("你好"));
            var response = chatModel.call(prompt);
            System.out.println("Response: " + response.getResult().getOutput().getText());
        } catch (Exception e) {
            System.err.println("Error calling chatModel.call(Prompt):");
            e.printStackTrace();
        }
        
        System.exit(0);
    }
}