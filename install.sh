#!/bin/bash


project_name=Basic


if [ -e $GG_HOME/generators/ ] && [ -e target/$project_name*.jar ]
then
	cp target/$project_name*.jar $GG_HOME/generators/
fi

