#!/bin/bash

#inputs
COMMAND=$1
USER_NAME=$2
PASSWORD=$3

#check no of arguments
if [ "$#" -ne 1 ] && [ "$#" -ne 3 ]; then
  echo "Illegal number of parameters"
  echo "Usage: ./psql_docker.sh create|start|stop [USER_NAME][PASSWORD]"
  exit 1
fi

#analyze docker status and start
if [ ! "$(sudo systemctl status docker )" ]; then
  systemctl start docker
fi

#command selection
case $COMMAND in
  "create")
    if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" -eq 2 ]; then
      echo "Error: 'jrvs-psql' already exists"
      echo "usage: ./psql_docker.sh start|stop"
      exit 1
    fi

    if  [ $# -ne 3 ]; then
      echo "Error: Invalid arguments. USERNAME and PASSWORD not given"
      echo "Usage: ./psql_docker.sh create [USERNAME][PASSWORD]"
      exit 1
    fi

    docker volume create pgdata
    docker run --name jrvs-psql -e POSTGRES_PASSWORD="${PASSWORD}" -e POSTGRES_USER="${USER_NAME}" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
    exit $?
    ;;

  "start")
    docker container start jrvs-psql
    exit $?
    ;;

  "stop")
    docker container stop jrvs-psql
    exit $?
    ;;

  *)
    echo "Error: Invalid arguments"
    echo "Usage: ./psql_docker.sh create|start|stop [USERNAME][PASSWORD]"
    exit 1
    ;;
esac
