 if [ ! -d /home/tomcat/temp/$1 ]
 then
 mkdir -p /home/tomcat/temp/$1
 chmod 777 /home/tomcat/temp/$1
 fi
 cd /home/tomcat/temp/$1
 jar -xvf /home/tomcat/war/$2
 if [ ! "`ls -A /home/tomcat/config/`" = "" ]
 then
 mv -f /home/tomcat/config/* /home/tomcat/temp/$1/WEB-INF/classes
 fi
 if [ -f $3/webapps/$2 ]
 then
 rm -rf $3/webapps/$2
 fi
 jar -cvf $3/webapps/$2 *
 rm -rf /home/tomcat/temp/$1
 echo 'deploy end'
