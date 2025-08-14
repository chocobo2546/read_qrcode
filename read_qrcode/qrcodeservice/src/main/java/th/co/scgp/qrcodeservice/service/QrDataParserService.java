package th.co.scgp.qrcodeservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface QrDataParserService {
    String parseQrData(MultipartFile file) throws Exception;
}

