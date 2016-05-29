#!/bin/bash
BASE_PATH=/usr/local/independence/apache2/htdocs

curl -o $BASE_PATH/index.html http://localhost:80/index

curl -o $BASE_PATH/post/scala/intro.md.html http://localhost:80/postDetail/1
curl -o $BASE_PATH/post/scala/development_environment.md.html http://localhost:80/postDetail/2
curl -o $BASE_PATH/post/scala/data_types.md.html http://localhost:80/postDetail/3
curl -o $BASE_PATH/post/scala/operators.md.html http://localhost:80/postDetail/4
curl -o $BASE_PATH/post/scala/control_structures.md.html http://localhost:80/postDetail/5
curl -o $BASE_PATH/post/scala/classes.md.html http://localhost:80/postDetail/6
curl -o $BASE_PATH/post/scala/inheritance.md.html http://localhost:80/postDetail/7
curl -o $BASE_PATH/post/scala/case_classes.md.html http://localhost:80/postDetail/8
curl -o $BASE_PATH/post/scala/functional_programming.md.html http://localhost:80/postDetail/9
curl -o $BASE_PATH/post/scala/higher_order_function_p1.md.html http://localhost:80/postDetail/10
curl -o $BASE_PATH/post/scala/higher_order_function_p2.md.html http://localhost:80/postDetail/11

curl -o $BASE_PATH/post/scala/index.html http://localhost:80/categories/scala

