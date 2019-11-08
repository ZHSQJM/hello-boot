package com.zhs.controller;

import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/1 9:45
 * @Description:
 * @version: 1.0
 */

@RestController
@Slf4j
@Api(description = "模拟的的API接口")
@RequestMapping("/v1/api/mock")
public class MockApiController2 {



    @GetMapping("/book/{id}/short_comment")
    @ApiOperation(value = "获取书籍短评",notes = "获取书籍短评")
    public Result latest(){

    Map mapr = new HashMap<>(10);
    List<Map> list = new ArrayList<>(16);
        Map map = new HashMap<>(10);
        map.put("content","i hate6!");
        map.put("nums",1);
        Map map1 = new HashMap<>(10);
        map1.put("content","i hate6!");
        map1.put("nums",1);
        Map map2 = new HashMap<>(10);
        map2.put("content","i hate6!");
        map2.put("nums",1);
        Map map3 = new HashMap<>(10);
        map3.put("content","i hate6!");
        map3.put("nums",1);
        list.add(map);list.add(map1);list.add(map2);list.add(map3);
        mapr.put("comment",list);
        mapr.put("book_id",1);
        return Result.success(mapr);
    }


    @PostMapping("/book/add/short_comment")
    @ApiOperation(value = "新增短评",notes = "新增短评")
    public Result previous(@PathVariable("id") String id){
        Map map = new HashMap<>(10);
        map.put("error_code","0");
        map.put("msg","ok");
        map.put("request","POST  /book/add_short_comment");
        return Result.success(map);
    }

    @GetMapping("/book/hot_keyword")
    @ApiOperation(value = "获取点赞信息",notes = "获取点赞信息")
    public Result favior(@PathVariable("type") Integer type,@PathVariable("id") Integer id){
        Map map = new HashMap<>(10);

        List<String> list = new ArrayList<>(16);
        list.add("Fluent Python");
        list.add("Python");
        list.add("小程序Java核心编程");
        list.add("慕课网7七月");

        map.put("hot",list);
        return Result.success(map);
    }


    @GetMapping("/book/search")
    @ApiOperation(value = "书籍搜索",notes = "书籍搜索")
    public Result Myfavior(){

        List<Map> list =  new ArrayList<>(16);

        Map map1 = new HashMap<>(10);
        map1.put("content","人生不能像做菜，把所有的料准备好才下锅");
        map1.put("fav_nums",1);
        map1.put("id",1);
        map1.put("image","http://bl.yushu.im/images/music.1.png");
        map1.put("pubdate","2018-06-22");
        map1.put("title","李安<<饮食男女>>");
        map1.put("type",100);

        Map map2 = new HashMap<>(10);
        map2.put("content","你陪我步入蝉夏 越过城市喧嚣");
        map2.put("fav_nums",1);
        map2.put("id",1);
        map2.put("image","http://bl.yushu.im/images/music.1.png");
        map2.put("pubdate","2018-06-22");
        map2.put("title","纸短情长");
        map2.put("type",200);
        map2.put("url","http://music.163.com/song/media/outer/url?id=557581284.mp3");
        list.add(map1);list.add(map2);
        return Result.success(list);
    }

    @GetMapping("/book/{id}/detail")
    @ApiOperation(value = "获取热门书籍(概要)",notes = "获取热门书籍(概要)")
    public Result hostList(@PathVariable("id") Integer id){
        List<String> list =  new ArrayList<>(16);
        list.add("Wolfgang Mauerer");
        Map map =new HashMap();
        map.put("author",list);
        map.put("binding","平装");
        map.put("category","算法");
        map.put("id",6980);
        map.put("image","https://img1.doubanio.com/lpic/s4368169.jpg");
        map.put("isbn","9787115227430");
        map.put("pages",1038);
        map.put("price","149.00元");
        map.put("pubdate", "201005");
        map.put("publisher","人民邮电出版社");
        map.put("subtitle","全球开源社区集体智慧结晶，领略Linux内核的绝美风光");
        map.put("summary","众所周知，Linux操作系统的源代码复杂、文档少，对程序员的要求高，要想看懂这些代码并不是一件容易事...");
        map.put("title","深入Linux内核架构");

        return Result.success(map);


    }


}
