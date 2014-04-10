# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table guest (
  id                        bigint not null,
  guest_one_name            varchar(255),
  guest_two_name            varchar(255),
  has_extra_guest           boolean,
  rsvp_code                 varchar(255),
  email                     varchar(255),
  phone_number              varchar(255),
  comments                  varchar(255),
  constraint pk_guest primary key (id))
;

create sequence guest_seq;




# --- !Downs

drop table if exists guest cascade;

drop sequence if exists guest_seq;

