import { configure } from '@kadira/storybook'
const r = require.context('../src', true, /\.story\.js$/)
configure(() => r.keys().forEach(r), module)
