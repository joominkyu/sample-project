package org.example.adminbackend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 파일 업로드 유틸 클래스
 *
 * - 파일 저장
 * - UUID 파일명 생성
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * 파일 저장
     *
     * @param file MultipartFile
     * @param uploadDir 저장 경로
     * @return 저장된 파일명
     */
    public static String saveFile(
            MultipartFile file,
            String uploadDir
    ) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalFilename =
                file.getOriginalFilename();

        String extension =
                getExtension(originalFilename);

        String savedFilename =
                UUID.randomUUID() + extension;

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File savedFile =
                new File(uploadDir, savedFilename);

        try {
            file.transferTo(savedFile);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }

        return savedFilename;
    }

    /**
     * 파일 확장자 추출
     */
    public static String getExtension(
            String filename
    ) {

        if (filename == null) {
            return "";
        }

        int index =
                filename.lastIndexOf(".");

        if (index == -1) {
            return "";
        }

        return filename.substring(index);
    }

}