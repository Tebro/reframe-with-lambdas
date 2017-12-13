# Serverless framework and re-frame example

Simple example project invoking a Lambda function via the AWS SDK in a clojurescript SPA.

## Requirements

- Leiningen
- serverless framework
- AWS account

## Setup

Deploy the serverless stack

```
cd serverless
sls deploy
```

Copy frontend/src/cljs/frontend/config.cljs.template to frontend/src/cljs/frontend/config.cljs and fill out the values. You can use the following command to get the outputs of the stack.

```
aws cloudformation describe-stacks --stack-name "backend-dev" |jq '.Stacks[0].Outputs'
```

Start the frontend development environment

```
cd frontend
lein figwheel dev
```

Browse to `http://localhost:3449`
