package com.springapi.donatecharity.service.log;

import com.springapi.donatecharity.utils.GsonParserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    private static final String REQUEST_ID = "request_id";
    private static final int MAX_BODY_LENGTH = 300;

    @Override
    public void logRequest(HttpServletRequest request, Object body) {
        if (request.getRequestURI().contains("medias")){
            return;
        }
        Object requestId = request.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();
        data.append("["+requestId+"] ")
                .append("["+request.getServerName()+"] ")
                .append("Request: ")

                .append("{")
                    .append(GsonParserUtils.parseObjectToString("url"))
                    .append(":")
                    .append(GsonParserUtils.parseObjectToString(request.getRequestURI()))


                    .append(",")

                    .append(GsonParserUtils.parseObjectToString("method"))
                    .append(":")
                    .append(GsonParserUtils.parseObjectToString(request.getMethod()))

                    .append(",")

                    .append(GsonParserUtils.parseObjectToString("json"))
                    .append(":")
                        .append("{")
                            .append(GsonParserUtils.parseObjectToString("data"))
                            .append(":")
                            .append(body)
                        .append("}")

                .append("}")
                .append("\n");
        log.info(data.toString());
    }

    @Override
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        if (request.getRequestURI().contains("medias")){
            return;
        }
        Object requestId = request.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();

        if (body != null && body.toString().length() < MAX_BODY_LENGTH) {
            // Hiển thị body của response
            data.append("[" + requestId + "] ")
                    .append("[" + request.getServerName() + "] ")
                    .append("Response: ")
                    .append("{")
                    .append(body)
                    .append("}")
                    .append("\n");
        } else {
            // Hiển thị chỉ trạng thái của response
            data.append("[" + requestId + "] ")
                    .append("[" + request.getServerName() + "] ")
                    .append("Response: ")
                    .append("{")
                    .append(GsonParserUtils.parseObjectToString("status"))
                    .append(":")
                    .append(response.getStatus())
                    .append("}")
                    .append("\n");
        }
        log.info(data.toString());

    }
}
