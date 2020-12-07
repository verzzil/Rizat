package ru.itis.services;
import ru.itis.dto.FileDto;
import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public interface FilesService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    List<FileDto> writeFilesFromStorage();
    FileInfo getFileInfo(Long fileId);
    void writeFileFromStorageById(Long id, OutputStream outputStream);

}
