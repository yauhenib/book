#!/bin/bash

if [ "$1" = "" ]
then
echo "Usage: credb <host_name_or_IP>."
exit
fi

psql -h $1 -U postgres -f credb.sql postgres
PGPASSWORD="12345678"
psql -h $1 -U bookuser -f book_init_data.sql bookdb