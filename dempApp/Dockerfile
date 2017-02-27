FROM java:openjdk-8-jre
ENV TERM xterm

# RUN apt-get update \
# 		&& apt-get install -y --no-install-recommends \
# 		curl \
# 		unzip \
# 		vim \
# 		wget \
# 		telnet \
# 		less \
# 		&& rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/demo/output && chmod 700 /opt/demo/output

COPY target/tuningForkDemoApp*.jar /opt/demo/
COPY etc/bootstrap.sh /etc/bootstrap.sh
RUN chown root:root /etc/bootstrap.sh && \
    chmod 700 /etc/bootstrap.sh

VOLUME /opt/demo/output

WORKDIR /opt/demo
EXPOSE 9000
EXPOSE 9001

CMD ["/etc/bootstrap.sh", "-d"]
