package com.alinebuchino.eventosTec.services;

import com.alinebuchino.eventosTec.DTOs.EventRequestDTO;
import com.alinebuchino.eventosTec.domain.Event;
import com.alinebuchino.eventosTec.repositories.EventRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3cliente;

    @Autowired
    private EventRepository repository;

    @Value("${aws.bucket.name}")
    private String awsBucketName;

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if (imgUrl != null) {
            imgUrl = this.uploadImg(data.image());
        }

        var newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setRemote(data.remote());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);

        repository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3cliente.putObject(awsBucketName, fileName, file);
            file.delete();

            return s3cliente.getUrl(awsBucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao enviar o arquivo na AWS");
            return "";
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
