package br.com.mycar.app.validations;

import br.com.mycar.app.exceptions.ContentTypeException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExtensionsValid {
    public static boolean validaFormato(MultipartFile arquivo) {
        //var extensoes, ext, valido;
        List<String> extensoes = new ArrayList<>();
        extensoes.add("image/png");
        extensoes.add("image/jpg");
        extensoes.add("image/jpeg");

        String extCatch = arquivo.getContentType();//arquivo.substring(arquivo.lastIndexOf(".")).toLowerCase();

        boolean valido = false;
        for(String str : extensoes){
                Objects.requireNonNull(extCatch);
                if(extCatch.equals(extensoes.get(0))){
                    valido = true;
                    break;
                }
        }

        if(valido){
            return true;
        }

        return false;

    }
}
