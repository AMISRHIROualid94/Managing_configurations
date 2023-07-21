package com.sbip.mc.Services;

import com.sbip.mc.ConfigProp.AppProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppService {
    @Autowired AppProperties appProperties;

}
