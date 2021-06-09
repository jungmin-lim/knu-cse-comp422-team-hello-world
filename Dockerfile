FROM ubuntu:20.04

RUN apt-get update && apt-get install -y openssh-server 
RUN apt-get -y install git gcc g++ python3 vim python3-pip
RUN apt-get -y install openjdk-11-jdk maven

RUN pip3 install essential_generators
RUN pip3 install uvicorn

RUN mkdir /var/run/sshd
RUN mkdir /root/.ssh
RUN echo 'root:sysprog04' | chpasswd

RUN sed -i 's/#*PermitRootLogin prohibit-password/PermitRootLogin yes/g' /etc/ssh/sshd_config
RUN sed -i 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' /etc/pam.d/sshd

ENV NOTVISIBLE="in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

EXPOSE 22
EXPOSE 80
EXPOSE 8080

CMD ["/usr/sbin/sshd", "-D"]

WORKDIR /root/
RUN mkdir /root/project
WORKDIR /root/project/

RUN git config --global user.email "jungmin.lim.cs@knu.ac.kr"
RUN git config --global user.name "jungmin"

ARG SSH_PRIVATE_KEY
RUN echo "$SSH_PRIVATE_KEY" >> ~/.ssh/id_rsa && chmod 600 ~/.ssh/id_rsa

RUN touch ~/.ssh/known_hosts
RUN ssh-keyscan -t rsa github.com >> ~/.ssh/known_hosts

RUN git clone git@github.com:jungmin-lim/knu-cse-comp422-team-hello-world.git

WORKDIR /root/project/knu-cse-comp422-team-hello-world/