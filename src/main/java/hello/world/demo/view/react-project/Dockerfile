FROM node:16

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json .

RUN npm install -g npm@8.13.1
#RUN npm i --force
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY ../.. .
RUN npm i --force --prefix src/main/java/hello/world/demo/view/react-project

EXPOSE 3000

CMD [ "npm", "start", "--prefix", "front-end/react-project"]
