// @ts-ignore
/* eslint-disable */
import request from '@/core/request.ts'

/** health GET /api/health */
export async function healthUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponse>('/api/health', {
    method: 'GET',
    ...(options || {}),
  })
}
