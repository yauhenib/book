@echo off
IF "%1"=="" GOTO helpscreen

psql -h %1 -U postgres -f credb.sql postgres
SET PGPASSWORD=12345678
psql -h %1 -U perfuser -f perf_init_data.sql perfdb

GOTO finish

:helpscreen
ECHO "Usage: credb <host_name_or_IP>."

:finish
@echo on

