cd $1
./bin/shutdown.sh
rm -rf ./work/Catalina
DATE=$(date +%Y%m%d%H%M%S)
mv -f ./logs/catalina.out ./logs/catalina-$DATE.log