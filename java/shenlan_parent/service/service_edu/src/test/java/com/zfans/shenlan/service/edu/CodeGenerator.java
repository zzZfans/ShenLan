package com.zfans.shenlan.service.edu;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @ClassName CodeGenerator
 * @Description Todo
 * @Author Zfans
 * @DateTime 2021/01/25 11:44
 */
public class CodeGenerator {

    @Test
    public void genCode() {

        String prefix = "";
        String moduleName = "edu";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Zfans");
        // 生成后是否打开资源管理器
        gc.setOpen(false);
        // // 重新生成时文件是否覆盖
        // gc.setFileOverride(false);
        // 去掉 Service 接口的首字母 I
        gc.setServiceName("%sService");
        // 主键策略
        gc.setIdType(IdType.ASSIGN_ID);
        // 定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 开启 Swagger2 模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:33306/" + prefix + "shenlan_" + moduleName + "?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("227859");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName); //模块名
        pc.setParent("com.zfans.shenlan.service");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 设置表前缀不生成
        strategy.setTablePrefix(moduleName + "_");
        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 模型 @Accessors(chain = true) setter 链式操作
        strategy.setEntityLombokModel(true);
        // 逻辑删除字段名
        strategy.setLogicDeleteFieldName("is_deleted");
        // 去掉布尔值的 is_ 前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        // 自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        // restful api 风格控制器
        strategy.setRestControllerStyle(true);
        // url 中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 设置 BaseEntity
        strategy.setSuperEntityClass("com.zfans.shenlan.service.base.model.BaseEntity");
        // 填写 BaseEntity 中的公共字段
        strategy.setSuperEntityColumns("id", "create_time", "update_time");

        // 6、执行
        mpg.execute();
    }
}
