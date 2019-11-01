package com.zhs.controller;

import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MockApiController {



    @GetMapping("/classic/latest")
    @ApiOperation(value = "获取最新一期",notes = "获取最新一期")
    public Result latest(){
        Map map = new HashMap<>(10);
        map.put("content","人生不能像做菜，把所有的料准备好才下锅");
        map.put("fav_nums",10);
        map.put("id",1);
        map.put("image","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572593139845&di=8874ee63edb6636b946a92dbf05cb970&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-17%2F5a0e7352bf79c.jpg");
        map.put("index",7);
        map.put("like_status",0);
        map.put("pubdate","2018-06-22");
        map.put("title","李安<<饮食男女>>");
        map.put("type",100);
        return Result.success(map);
    }

    @GetMapping("/classic/{id}/next")
    @ApiOperation(value = "获取当前一期的下一期",notes = "获取当前一期的下一期")
    public Result next(@PathVariable("id") Integer id){
        Map map = new HashMap<>(10);
        map.put("content","这个夏天又是一个毕业季");
        map.put("fav_nums",0);
        map.put("id",2);
        map.put("image","http://img2.imgtn.bdimg.com/it/u=2708495562,4008897354&fm=26&gp=0.jpg");
        map.put("index",2);
        map.put("like_status",0);
        map.put("pubdate","2018-06-22");
        map.put("title","未名");
        map.put("type",300);
        return Result.success(map);
    }


    @GetMapping("/classic/{type}/{id}")
    @ApiOperation(value = "获取某一期详细信息",notes = "获取某一期详细信息")
    public Result next(@PathVariable("type") Integer type,@PathVariable("id") Integer id){
        Map map = new HashMap<>(10);
        map.put("content","这个夏天又是一个毕业季1");
        map.put("fav_nums",0);
        map.put("id",2);
        map.put("image","http://img4.imgtn.bdimg.com/it/u=4283313049,3014632291&fm=26&gp=0.jpg");
        map.put("index",2);
        map.put("like_status",0);
        map.put("pubdate","2018-06-22");
        map.put("title","未名");
        map.put("type",300);
        return Result.success(map);
    }


    @GetMapping("/classic/{id}/previous")
    @ApiOperation(value = "获取当前一期的上一期",notes = "获取当前一期的上一期")
    public Result previous(@PathVariable("id") Integer id){
        Map map = new HashMap<>(10);
        map.put("content","你陪我步入蝉夏 越过城市喧嚣");
        map.put("fav_nums",0);
        map.put("id",3);
        map.put("image","http://img4.imgtn.bdimg.com/it/u=2638839895,2846473351&fm=26&gp=0.jpg");
        map.put("index",1);
        map.put("like_status",0);
        map.put("pubdate","2018-06-22");
        map.put("title","纸短情长");
        map.put("type",200);
        map.put("url","http://music.163.com/song/media/outer/url?id=557581284.mp3");
        return Result.success(map);
    }

    @GetMapping("/classic/{type}/{id}/favior")
    @ApiOperation(value = "获取点赞信息",notes = "获取点赞信息")
    public Result favior(@PathVariable("type") Integer type,@PathVariable("id") Integer id){
        Map map = new HashMap<>(10);
        map.put("fav_nums",1);
        map.put("like_status",1);
        map.put("id",1);
        return Result.success(map);
    }


    @GetMapping("/classic/favior")
    @ApiOperation(value = "获取我喜欢的期刊",notes = "获取我喜欢的期刊")
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

    @GetMapping("/book/hot_list")
    @ApiOperation(value = "获取热门书籍(概要)",notes = "获取热门书籍(概要)")
    public Result hostList(){
        List<Map> list =  new ArrayList<>(16);
        Map map1 = new HashMap<>(10);
        map1.put("author","陈儒");
        map1.put("fav_nums",0);
        map1.put("id",1);
        map1.put("image","https://img3.doubanio.com/lpic/s3435132.jpg");
        map1.put("like_status",1);
        map1.put("title","Python源码剖析");

        Map map2 = new HashMap<>(10);
        map2.put("author","MarkPilgrim");
        map2.put("fav_nums",1);
        map2.put("id",1);
        map2.put("image","https://img3.doubanio.com/lpic/s3435132.jpg");
        map2.put("like_status",1);
        map2.put("title","Dive Into Python");

        Map map3 = new HashMap<>(10);
        map3.put("author","MarkPilgrim");
        map3.put("fav_nums",1);
        map3.put("id",1);
        map3.put("image","https://img3.doubanio.com/lpic/s3435132.jpg");
        map3.put("like_status",1);
        map3.put("title","Dive Into Python 3");

        list.add(map1); list.add(map2); list.add(map3);
        return Result.success(list);
    }
}
