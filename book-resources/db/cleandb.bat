@echo off
IF "%1"=="" GOTO helpscreen

psql -h %1 -U bookuser -f clean_db.sql bookdb

GOTO finish

:helpscreen
ECHO "Usage: cleandb <host_name_or_IP>."

:finish
@echo on
