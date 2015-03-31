FROM jetty
MAINTAINER Kasper Rune Søgaard <krs@tradeshift.com>

# Move the WAR file to jetty location
ADD root.war /var/lib/jetty/webapps/

# Start Jetty, cd to the jetty folder so it knows where to looks for jars
# This is the entry point
CMD ["jetty.sh", "run"]