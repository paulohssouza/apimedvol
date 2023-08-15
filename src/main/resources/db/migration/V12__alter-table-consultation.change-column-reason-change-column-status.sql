alter table consultation drop column reason;
alter table consultation drop column status;
alter table consultation add column reason varchar(100);
alter table consultation add column status varchar(100);