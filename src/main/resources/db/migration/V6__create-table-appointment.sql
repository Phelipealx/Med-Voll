create table appointment(

    id bigint not null auto_increment,
    medic_id bigint not null,
    patient_id bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_appointment_medic_id foreign key(medic_id) references medic(id),
    constraint fk_appointment_patient_id foreign key(patient_id) references patient(id)

);