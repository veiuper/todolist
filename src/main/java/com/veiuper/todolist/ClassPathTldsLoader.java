package com.veiuper.todolist;

import freemarker.ext.jsp.TaglibFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassPathTldsLoader {
    static final String SECURITY_TLD = "/META-INF/security.tld";
    final List<String> classPathTlds;
    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    public ClassPathTldsLoader(String... classPathTlds) {
        super();
        if (classPathTlds.length == 0) {
            this.classPathTlds = Collections.singletonList(SECURITY_TLD);
        } else {
            this.classPathTlds = Arrays.asList(classPathTlds);
        }
    }

    @PostConstruct
    public void loadClassPathTlds() {
        TaglibFactory tagLibFactory = freeMarkerConfigurer.getTaglibFactory();
        tagLibFactory.setClasspathTlds(classPathTlds);
        if (tagLibFactory.getObjectWrapper() == null) {
            tagLibFactory.setObjectWrapper(freeMarkerConfigurer.getConfiguration().getObjectWrapper());
        }
    }
}
