# Cell
1.创建Cell
```java
Row.createCell(i) //i 表示该行索引，从0开始
```
<font color="red">下面方法弃用</font>
```java
#service.addCell(...) 
```

2.填写公式
```java
Cell.setCellFormula(formula) //formula 表示公式，不需要开头等号
```
3.透视表
```java
XSSFPivotTable pivotTable = sheet3.createPivotTable(new AreaReference("Sheet2!A1:C11", null), new CellReference("Sheet3!C3"));//创建透视表，null表示默认sheet版本
pivotTable.addRowLabel(index); //添加行标签
pivotTable.addColumnLabel(DataConsolidateFunction.SUM, index,colName); //添加列标签，colname设置列名,SUM表示求和

```
