package com.majiang.community.community.cache;

import com.majiang.community.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/20
 */
public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js", "php", "css", "html", "java", "node", "python", "C++", "C", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO frameDTO = new TagDTO();
        frameDTO.setCategoryName("平台框架");
        frameDTO.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rail", "tornado", "koa", "struts"));
        tagDTOS.add(frameDTO);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "hadoop", "windows-server"));
        tagDTOS.add(server);


        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hq"));
        tagDTOS.add(tools);
        return tagDTOS;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        //获取最里层的数据
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));


        return invalid;
    }
}
