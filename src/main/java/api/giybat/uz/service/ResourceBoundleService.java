package api.giybat.uz.service;

import api.giybat.uz.enums.AppLangulage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBoundleService {
    @Autowired
    private MessageSource boundleMessage;

    public String getMessage(String code, AppLangulage lang) {
        return boundleMessage.getMessage(code, null, new Locale(lang.name()));
    }
}
