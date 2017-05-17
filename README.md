Example of using om.next components in React Storybook.

This example also uses the JS dependencies solution as described here:
http://blob.tomerweller.com/reagent-import-react-components-from-npm

This way we can use the latest versions of JS libs without worrying about
externs.

The trade off is that you must use string names for properties, but for that
we can use:
https://github.com/binaryage/cljs-oops

# Overview of steps to get things setup:

This project was created using a figwheel template:

```bash
lein new figwheel cljs-storybook -- --om
```

Then updating the `project.clj`, and creating a simple component:

```clojure
(defui ^:export Hello
  Object
  (render [this]
    (let [{:keys [name]} (om/props this)]
      (dom/div nil (str " your name is: " name)))))

(def ^:export hello (om/factory Hello))
```

Next step is to install all the JS deps using yarn.

This assumes yarn, npm, and node are installed already.

```bash
yarn init -y
yarn add -D \
react \
react-dom \
webpack \
@kadira/storybook
```

Setup React Storybook

Following the Storybook "slow start" guide

https://storybooks.js.org/docs/react-storybook/basics/slow-start-guide/

Add npm/yarn script:

```
  "scripts": {
    "storybook": "start-storybook -p 9001 -c .storybook -s resources/public"
  }
```

`-p` is the port to run the server on, `-c` is where to find the config directory
and `-s` adds more directories for the server to serve static files from.

`resources/public` is where our compiled CLJS and JS files are output to.

Create `.storybook` dir.

Edit `.storybook/config.js`

We use webpack's require.context feature for dynamic require statements.

https://webpack.js.org/guides/dependency-management/#require-context


This will treat any file ending in `.story.js` as a file that contains stories.
You are free to change how stories are included.

```javascript
import { configure } from '@kadira/storybook'
const r = require.context('../src', true, /\.story\.js$/)
configure(() => r.keys().forEach(r), module)
```

Produce JS deps:

```bash
 ./node_modules/.bin/webpack -p
```

Add `head.html` in `.storybook` dir:

This includes our CLJS on the page in a blocking manner so that our
namespaces will be available globally before the story code runs.

```html
<script src="js/compiled/out/goog/base.js"></script>
<script src="js/compiled/cljs_storybook.js"></script>
<script>goog.require('cljs_storybook.core')</script>
```

Now we can construct a story:

```javascript
import React from 'react'
import { storiesOf } from '@kadira/storybook'
const {hello} = cljs_storybook.core
const props = cljs.core.hash_map(cljs.core.keyword('name'), 'hello')

storiesOf('OmComponent', module)
  .add('Default', () => <div>{hello(vals)}</div>)
```  

In this example I'm constructing the CLJS map in JS, another solution
would be to export your props from CLJS so they are easier to write.

Start storybook:

```
yarn run storybook
```

Either produce a CLJS build one time or for dev use figwheel:

```bash
lein cljsbuild once dev
lein cljsbuild once min
# or
rlwrap lein figwheel
```

open http://localhost:9001 to view the stories.
and http://localhost:3449 for normal figwheel dev.

Now you can edit your code and use figwheel development workflow while viewing
your components in React Storybook.
