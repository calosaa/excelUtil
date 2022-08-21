use ccb_timecard;
-- ==============个人================
-- 上午旷工次数
select count(*) from time_table a where a.type=5 and a.name='徐岚' and m_o_a=true;
-- 下午旷工次数
select count(*) from time_table a where a.type=5 and a.name='徐岚' and m_o_a=false;
-- 总旷工次数
select  count(distinct a.day,b.day) from time_table a right join time_table b on a.type=b.type and a.name=b.name and a.day=b.day where a.type=5 and a.name='周福彬';
-- 迟到次数
select count(*) from time_table a where a.type=1 and a.name='周福彬';

-- 未打卡
select distinct t.name from time_table t right join  staff s on t.name = s.name where s.dep_name='派驻纪检组' and t.type=5;

truncate time_table;
truncate staff;

name='许媛' and month=1 and day in (26)

update time_table set comments='刷脸失败' where name='罗汉平' and type=0 and day between 4 and 26 and on_time IS NULL;

select distinct s.dep_name from staff s;

select s.name from staff s where s.dep_name='风险管理部';

select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=5 and s.dep_name='派驻纪检组' and month=1;

select distinct s.dep_name from staff s;
-- 人数
select count(*) from staff s where s.dep_name='派驻纪检组';

-- 总打卡次数
select distinct count(*) from time_table t right join staff s on t.name = s.name where (t.type=5 or t.type=0 or t.type=6 or t.type=4);
-- 应打卡人次
select distinct count(*) from time_table t right join staff s on t.name = s.name where (t.type=5 or t.type=0) and month=1;
-- 实际打卡人次
select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=0 and month=1;
