/* sort GD giảm dần */
select * from epl.team
order by GD DESC;

/* sort GD tăng dần */
select * from epl.team
order by GD;

/* liệt kê điểm số của các đội có <10 trận thua + sort từ thấp đến cao điểm nhất*/
select teamname,pts from epl.team
where (L<10)
order by pts ASC;

/* liệt kê các cầu thủ ghi được min 20 bàn trong danh sách top 10 scorer */
select playername,team from epl.topscorer
where goal>=20;

/* đếm số cầu thủ thi đấu cho ARS trong danh sách top 10 scorer */
select COUNT(*)
from epl.topscorer
where team="Arsenal";

/* tên các CLB có cầu thủ trong top 10 scorer */
select team from epl.topscorer
group by team; /* gộp những thằng trùng tên lại /*

/* add cột xG vào giữa cột team và goal ở bảng topscorer */
ALTER TABLE epl.topscorer add column xG int after team;