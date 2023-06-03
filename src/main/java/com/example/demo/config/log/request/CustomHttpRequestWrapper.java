package com.example.demo.config.log.request;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

@Slf4j
class CustomHttpRequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream inputStreamSaver = new ByteArrayOutputStream();

    public CustomHttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        request.getInputStream().transferTo(inputStreamSaver);

        String bodyInStringFormat = readInputStreamInStringFormat(Charset.forName(request.getCharacterEncoding()));
        log.info("Request to {} {} body: {} ",
                request.getMethod(), request.getRequestURI(),
                formatString(bodyInStringFormat)
        );
    }
    private String readInputStreamInStringFormat(Charset charset) throws IOException {
        final StringBuilder bodyStringBuilder = new StringBuilder();

        InputStream stream = new ByteArrayInputStream(inputStreamSaver.toByteArray());

        final byte[] entity = new byte[inputStreamSaver.size() +1];
        final int bytesRead = stream.read(entity);
        if (bytesRead != -1) {
            bodyStringBuilder.append(new String(entity, 0, bytesRead , charset));
        }else{
            bodyStringBuilder.append("Wasnt possible to read the body!");
        }

        return bodyStringBuilder.toString();
    }

    private String formatString(String toFormat){

        return toFormat.replaceAll("[\\r\\n]+", "").trim().replaceAll("\\s{2,}", " ");
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream () throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputStreamSaver.toByteArray());

        return new ServletInputStream() {
            private boolean finished = false;

            @Override
            public boolean isFinished() {
                return finished;
            }

            @Override
            public int available() throws IOException {
                return byteArrayInputStream.available();
            }

            @Override
            public void close() throws IOException {
                super.close();
                byteArrayInputStream.close();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }

            public int read () throws IOException {
                int data = byteArrayInputStream.read();
                if (data == -1) {
                    finished = true;
                }
                return data;
            }
        };
    }
}