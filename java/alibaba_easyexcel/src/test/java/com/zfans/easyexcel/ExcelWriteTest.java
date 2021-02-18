package com.zfans.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zfans.easyexcel.entity.ExcelStudentData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/02/05 17:21
 */
public class ExcelWriteTest {
    /**
     * 最简单的写
     */
    @Test
    public void simpleWrite07() {

        String fileName = "src/test/java/com/zfans/easyexcel/excel/01-simpleWrite-07.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel
                .write(fileName, ExcelStudentData.class)
                .sheet("模板")
                .doWrite(data());
    }

    @Test
    public void simpleWrite03() {

        String fileName = "src/test/java/com/zfans/easyexcel/excel/01-simpleWrite-03.xls";
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel
                .write(fileName, ExcelStudentData.class)
                .excelType(ExcelTypeEnum.XLS)
                .sheet("模板")
                .doWrite(data());
    }

    private List<ExcelStudentData> data() {
        List<ExcelStudentData> list = new ArrayList<>();

        //算上标题，做多可写65536行
        //超出：java.lang.IllegalArgumentException: Invalid row number (65536) outside allowable range (0..65535)
        for (int i = 0; i < 65535; i++) {
            ExcelStudentData data = new ExcelStudentData();
            data.setName("Helen" + i);
            data.setBirthday(new Date());
            data.setSalary(0.56);
            data.setPassword("123"); //即使设置也不会被导出
            list.add(data);
        }

        return list;
    }

}
