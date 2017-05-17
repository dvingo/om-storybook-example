import React from 'react'
import { storiesOf } from '@kadira/storybook'
const {Hello, hello} = cljs_storybook.core
const vals = cljs.core.hash_map(cljs.core.keyword('name'), 'hello')
const props = {
  "omcljs$value": om.next.om_props(vals, 0)
}

storiesOf('OmComponent', module)
  .add('Default', () => (
    <div>
    {React.createElement(Hello, props)}
    {hello(vals)}
    <button>Hello Button</button>
    </div>
  ))
