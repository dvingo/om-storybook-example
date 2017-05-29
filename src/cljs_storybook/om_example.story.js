import React from 'react'
import { storiesOf } from '@kadira/storybook'
const {hello, canvas_example, data} = cljs_storybook.core

storiesOf('OmComponents', module)
  .add('Simple', () => (
    <div>
    {hello(data)}
    <button>Hello Button</button>
    </div>
  ))
  .add('Canvas', () => (
    <div>{canvas_example()}</div>
  ))
