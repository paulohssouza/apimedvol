create table consultation(
    id bigint not null auto_increment,
    id_doctor bigint not null,
    id_patient bigint not null,
    date datetime not null,
    primary key (id),
    constraint fk_consultation_id_doctor foreign key (id_doctor) references doctor(id),
    constraint fk_consultation_id_patient foreign key (id_patient) references patient(id)
);