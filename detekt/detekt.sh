#!/bin/bash

echo "Running detekt check..."
fileArray=($@)
detektInput=$(IFS=,;printf  "%s" "${fileArray[*]}")
echo "Input files: $detektInput"

OUTPUT=$(detekt --config detekt/detekt-config.yml --build-upon-default-config --input "$detektInput" 2>&1)
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
  echo "                                               "
  echo "************* code analysis output ************"
  echo "                                               "
  echo $OUTPUT | sed 's/ r /\n/g; s/]/]\n/g'
  echo "                                               "
  echo "***********************************************"
  echo "                 detekt failed                 "
  echo " Please fix the above issues before committing "
  echo "***********************************************"
  echo "                                               "
  exit $EXIT_CODE
fi
