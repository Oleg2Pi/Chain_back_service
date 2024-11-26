package by.polikarpov.backend.service;

import by.polikarpov.backend.entity.ImageOfPerson;
import by.polikarpov.backend.entity.Work;
import by.polikarpov.backend.exceptions.FileSaveException;
import by.polikarpov.backend.repository.ImageOfPersonRepository;
import by.polikarpov.backend.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FilesService {

    private final WorkRepository workRepository;
    private final ImageOfPersonRepository imageOfPersonRepository;

    @Autowired
    public FilesService(WorkRepository workRepository, ImageOfPersonRepository imageOfPersonRepository) {
        this.workRepository = workRepository;
        this.imageOfPersonRepository = imageOfPersonRepository;
    }

    public void saveWork(MultipartFile file, Integer workId) throws FileSaveException {
        try {
            Work work;

            if (workRepository.findById(workId).isPresent()) {
                work = workRepository.findById(workId).get();
            } else {
                throw new FileSaveException("File was not saved");
            }
            String filePath = work.getFile();
            file.transferTo(new File(filePath));

            File savedFile = new File(filePath);
            if (!savedFile.exists() && !savedFile.isFile()) {
                throw new FileSaveException("File was not created after save operation");
            }
        } catch (IOException e) {
            throw new FileSaveException("File was not saved");
        }
    }

    public String getWorkFilePath(Integer workId) {
        if (workRepository.findById(workId).isPresent()) {
            Work work = workRepository.findById(workId).get();
            return work.getFile();
        }

        return "";
    }

    public String getPersonImageFilePath(Long chatId) {
        ImageOfPerson image = imageOfPersonRepository.findByPersonChatId(chatId);
        return image.getFilePath();
    }
}
