package site.daipeng.boot.demo.controller;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daipeng
 * @date 2019/10/17 17:44
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("uploadFile")
    public Map<String, Object> uploadMultiFile(@RequestParam String label_id, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        System.err.println("label_id=" + label_id);
        System.err.println("file=" + file.getOriginalFilename());
        return result;
    }

    @GetMapping("upload")
    public String test() throws  Exception {

//        FileInputStream fileInputStream = new FileInputStream(new File("F:\\by2019h1.zip"));
        FileInputStream fileInputStream = new FileInputStream(new File("F:\\1075.tar"));


        final InputStream fis = new FileInputStream(new File("F:\\1075.tar")); // or whatever
        final RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(final ClientHttpRequest request) throws IOException {
                request.getHeaders().add("Content-type", "application/octet-stream");
                IOUtils.copy(fis, request.getBody());
            }
        };
        final RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate.setRequestFactory(requestFactory);
        final HttpMessageConverterExtractor<String> responseExtractor =
                new HttpMessageConverterExtractor<String>(String.class, restTemplate.getMessageConverters());
        String s = restTemplate.execute("http://localhost:9999/test/uploadFile?label_id=123", HttpMethod.POST, requestCallback, responseExtractor);
        System.err.println(s);

        return "success";



    }

    @GetMapping("upload2")
    public String test2() throws  Exception {

        FileInputStream fileInputStream = new FileInputStream(new File("F:\\1075.tar"));
//        File file = new File("F:\\1075.tar");
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        MultiValueMap<String, Object> parts =
                new LinkedMultiValueMap<>();
        parts.add("file", new ByteArrayResource(buffer));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(parts, headers);


        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:9999/test/uploadFile?label_id=123",
                        HttpMethod.POST, requestEntity, String.class);
        System.err.println(response.getBody());
        return "sss";

    }
    @GetMapping("upload3")
    public String test3() throws  Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("F:\\1075.tar"));
//        File file = new File("F:\\1075.tar");
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("myfile")
                .filename("123")
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<InputStream> fileEntity = new HttpEntity<>(fileInputStream, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("myfile", fileEntity);
        body.add("label_id", 123);



        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
//                    "http://tagtest.inner.yuewen.local/tag/upload",
                    "http://localhost:9999/test/uploadFile",
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
            System.err.println(JSON.toJSONString(response));

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return "test3";

    }
    @GetMapping("upload4")
    public String test4() throws  Exception {
        MultiValueMap<String,Object> parts = new LinkedMultiValueMap<>();
        FileInputStream fileInputStream = new FileInputStream(new File("F:\\1075.tar"));

        MultipartFileResource file = new MultipartFileResource(fileInputStream);
        parts.add("file", file);
        parts.add("label_id", "123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(parts, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:9999/test/uploadFile", HttpMethod.POST, request, String.class);
        System.err.println(JSON.toJSONString(responseEntity));

        return "test3";

    }
    public class MultipartFileResource extends ByteArrayResource {

        private String filename;

        public MultipartFileResource(MultipartFile multipartFile) throws IOException {
            super(multipartFile.getBytes());
            this.filename = multipartFile.getOriginalFilename();
        }

        @Override
        public String getFilename() {
            return this.filename;
        }
    }
}
