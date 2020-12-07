package ru.itis.services;
import ru.itis.dto.FileDto;
import ru.itis.models.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import ru.itis.repositories.FilesRepository;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FilesServiceImpl implements FilesService {

    private FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFileName))
                .size(size)
                .type(contentType)
                .build();

        try {
            Files.copy(file, Paths.get("C://files/" + fileInfo.getStorageFileName()));
            filesRepository.save(fileInfo);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<FileDto> writeFilesFromStorage() {
        try {
            List<FileInfo> allFiles = filesRepository.findAll();
            BASE64Encoder encoder = new BASE64Encoder();
            List<FileDto> encodedFiles = new ArrayList<>();
            for (FileInfo fileInfo : allFiles) {
                File file = new File("C://files/" + fileInfo.getStorageFileName());
                FileDto fileDto = FileDto.builder()
                        .filename(fileInfo.getOriginalFileName())
                        .content("data:image/png;base64," + encoder.encode(FileUtils.readFileToByteArray(file)))
                        .build();
                encodedFiles.add(fileDto);
            }
            return encodedFiles;
        } catch (IOException e) {
            return null;
        }
    }



    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId);
    }

    @Override
    public void writeFileFromStorageById(Long id, OutputStream outputStream) {
        FileInfo fileInfo = filesRepository.findById(id);
        try {
            File file = new File("C://files/" + fileInfo.getStorageFileName());
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

    }
}
