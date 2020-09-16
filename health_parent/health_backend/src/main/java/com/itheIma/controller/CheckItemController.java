package com.itheIma.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheIma.constant.MessageConstant;

import com.itheIma.entity.PageResult;
import com.itheIma.entity.QueryPageBean;
import com.itheIma.entity.Result;
import com.itheIma.pojo.CheckItem;
import com.itheIma.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*** 体检检查项管理 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    //新增
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Result add(@RequestBody CheckItem checkItem){
//        checkItemService.add(checkItem);

        try {

            checkItemService.add(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    //分页查询
    @RequestMapping(value = "/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryP){
        PageResult pageR=null;
        pageR=checkItemService.pageQuery(
                queryP.getCurrentPage(),
                queryP.getPageSize(),
                queryP.getQueryString());

        return  pageR;
    }

    @RequestMapping( value = "/delete")
    public Result delete(Integer id){
        try{
            checkItemService.delete(id);
        }
        catch(Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);

    }
    //编辑
    @RequestMapping(value = "/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try{
            CheckItem checkItem = checkItemService.findById(id);

             return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }catch (Exception e){
            e.printStackTrace();
        //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL); }
    }

    @RequestMapping(value = "/findAll")
    public Result findAll(){
        List<CheckItem> checkItemList = checkItemService.findAll();
        if(checkItemList != null && checkItemList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
            result.setData(checkItemList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }
}