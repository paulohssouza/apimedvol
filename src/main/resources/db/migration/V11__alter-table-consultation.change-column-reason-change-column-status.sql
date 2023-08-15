alter table consultation drop column reason;
alter table consultation drop column status;
alter table consultation add column reason enum ('PACIENTE_DESISTIU', 'MEDICO_CANCELOU', 'OUTROS');
alter table consultation add column status enum ('CONFIRMADO', 'CANCELADO');