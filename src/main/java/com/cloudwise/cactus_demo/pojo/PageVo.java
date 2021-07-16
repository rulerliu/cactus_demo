package com.cloudwise.cactus_demo.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    private Long current;
    private Long size;
    private Long total;
    private List<T> data;

    public static PageVo pageResult(IPage page) {
        PageVo pageVo = new PageVo();
        pageVo.setCurrent(page.getCurrent());
        pageVo.setSize(page.getSize());
        pageVo.setTotal(page.getTotal());
        pageVo.setData(page.getRecords());
        return pageVo;
    }
}
