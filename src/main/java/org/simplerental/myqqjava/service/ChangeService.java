package org.simplerental.myqqjava.service;

import lombok.RequiredArgsConstructor;
import org.simplerental.myqqjava.config.ResponseMessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Service
@RequiredArgsConstructor
public class ChangeService {
    public final ClientService clientService;

    public String processChangeRequest(String loginId, BufferedReader reader, PrintWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {

            String lineTrim = line.trim();
            if ((lineTrim.equalsIgnoreCase("CHECK"))) {
                clientService.handleOnlineFriends(loginId,writer);
            } else {
                String responseMessage = clientService.handleResponseMessage(line,loginId);
                if (!responseMessage.equals(ResponseMessage.Successful.getMessage()) ){
                    writer.println(responseMessage);
                } else {
                    writer.println(responseMessage);
                    return clientService.lineSplit(line).get(1);
                }
            }
        }
        return null;
    }

}
