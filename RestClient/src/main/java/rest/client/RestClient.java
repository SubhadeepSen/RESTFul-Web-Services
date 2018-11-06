package rest.client;

import java.util.List;

import com.model.Message;
import com.operation.HttpOperation;

public class RestClient {
	public static void main(String[] args) {
		HttpOperation httpOperation = new HttpOperation();
		httpOperation.initClient();
		/*System.out.println(httpOperation.sendGetRequest(101).getMessage());
		Message newMessage = new Message(789, "This is rest client", "Staylone");
		System.out.println(httpOperation.sendPosttRequest(newMessage).getMessage());*/
		List<Message> list = httpOperation.sendGetRequestForAllMessage();
		for (Message message : list) {
			System.out.println(message.getId()+"\t"+message.getMessage());
		}
	}
}