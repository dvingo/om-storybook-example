#!/bin/bash -

outfile=cljs_storybook.js
rm -f resources/public/js/compiled/$outfile
lein cljsbuild once min
yarn run storybook-build
cp resources/public/js/compiled/$outfile storybookBuild/static/
